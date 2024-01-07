package study.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import study.domain.Member;
import study.jwt.tokenDto.JwtTokenDto;
import study.repository.MemberRepository;

import java.util.Date;


//Jwt token을 관리하는 클래쓰
//주로 메서드: 토큰 생성, 토큰으로 authentication 객체 생성, 토큰 유효성 검사 존재

@Component
public class JwtTokenProvider {
    // 토큰 유효성 검사,발금 등등
    private static final String AUTHORITIES_KEY = "auth";
    private static final String BEARER_TYPE = "Bearer";
    private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 180; // 3시간
    private static final long REFRESH_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24 * 7; // 7주일

    private final String key;
    private final MemberRepository memberRepository;

    @Autowired
    public JwtTokenProvider(@Value("${jwt.secret}") String secretKey, MemberRepository memberRepository) {
        this.key = secretKey;
        this.memberRepository = memberRepository;
    }

    public JwtTokenDto generateJwtTokenDto(Member member) {
        //access 토큰은 3시간, refresh 토큰은 일주일의 유효시간을 가지도록 생성되며,
        //JwtTokenDto 객체에 담겨서 반환됨
        //access 토큰의 subject로 member의 email을, claim으로는 id와 name을 가지도록 설정
        long now = (new Date()).getTime();

        // Access Token 생성
        Date accessTokenExpiresIn = new Date(now + ACCESS_TOKEN_EXPIRE_TIME);
        String accessToken = JWT.create()
                .withSubject(member.getEmail())                                       // payload "sub": "member.email"
                .withClaim("id", member.getId())                                // payload "id": "member.id"
                .withClaim("name", member.getName())            // payload "authority": "member.role"
                .sign(Algorithm.HMAC256(key));                                        // header "alg": "HS256"

        // Refresh Token 생성
        String refreshToken = JWT.create()
                .withExpiresAt(new Date(now + REFRESH_TOKEN_EXPIRE_TIME))
                .sign(Algorithm.HMAC256(key));

        return JwtTokenDto.builder()
                .grantType(BEARER_TYPE)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .accessTokenExpiresIn(accessTokenExpiresIn.getTime())
                .build();
    }

    public Authentication getAuthentication(String accessToken) {
        //access 토큰으로 emmber 객체를 db로부터 꺼내오고, 해당 member 객체를 토대로 principalDetails 생성
        //만든 PrincipalDetails 객체를 바탕으로 Authentication 객체를 생성 후, 반환

        // Authentication 객체 만들기
        Long memberId = JWT.require(Algorithm.HMAC256(key)).build().verify(accessToken).getClaim("id").asLong();

        // 예외처리 수정 필수
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new RuntimeException("사용자가 존재하지 않습니다.")
        );

        PrincipalDetails principalDetails = new PrincipalDetails(member);

        return new UsernamePasswordAuthenticationToken(
                principalDetails,
                "",
                principalDetails.getAuthorities()
        );
    }

    public boolean validateToken(String token) {
        // 토큰의 유효성 검사를 진행하고 유효하면 true, 유효하지 않으면 AuthenticationServiceException을 throw
        // 예외 처리 로직은 수정 예정
        try {
            JWT.require(Algorithm.HMAC256(key)).build().verify(token);
            return true;
        //} catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
        //    System.out.println("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            System.out.println("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            System.out.println("지원되지 않는 JWT 토큰입니다.");
        }catch (IllegalArgumentException e){
            System.out.println("JWT 토큰이 잘못되었습니다.");
        }
        return false;
    }

}
