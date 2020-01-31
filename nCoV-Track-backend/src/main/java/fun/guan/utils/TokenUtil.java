package fun.guan.utils;


import fun.guan.config.SysConstant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

public class TokenUtil {

//	private static String keyStr = "ma987ian4i91890872098n~!57862()()++_)__)_)_)(&*&&^$R%$$@$^&(*HYBVTHE$#F6b77uitb65ef56";

	/**
	 * 创建jwt
	 * @param id
	 * @param issuer
	 * @param subject
	 * @return
	 */
	public static String createJWT(String id, String issuer, String subject) {

		//The JWT signature algorithm we will be using to sign the token
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);

		//We will sign our JWT with our ApiKey secret
		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SysConstant.getJwtSeed());
		Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

		//Let's set the JWT Claims
		JwtBuilder builder = Jwts.builder().setId(id)
				.setIssuedAt(now)
				.setSubject(subject)
				.setIssuer(issuer)
				.signWith(signatureAlgorithm, signingKey);

		//if it has been specified, let's add the expiration
//		if (ttlMillis >= 0) {
//			long expMillis = nowMillis + ttlMillis;
		long expMillis = nowMillis + 1000 * 60 * 60 * 24;
		Date exp = new Date(expMillis);
		builder.setExpiration(exp);
//		}
		//Builds the JWT and serializes it to a compact, URL-safe string
		return builder.compact();
	}




	/**
	 * Sample method to validate and read the JWT
	 *
	 * @param jwt jwt
	 * @return result
	 */
	public static boolean parseJWT(String jwt) {
		//This line will throw an exception if it is not a signed JWS (as expected)
		try {
			Claims claims = Jwts.parser()
					.setSigningKey(DatatypeConverter.parseBase64Binary(SysConstant.getJwtSeed()))
					.parseClaimsJws(jwt).getBody();
//			System.out.println("ID: " + claims.getId());
//			System.out.println("Subject: " + claims.getSubject());
//			System.out.println("Issuer: " + claims.getIssuer());
//			System.out.println("Expiration: " + claims.getExpiration());
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("解码发生错误");
			return false;
		}

	}


	public static String getUserId(String jwt) {
		//This line will throw an exception if it is not a signed JWS (as expected)
		try {
			Claims claims = Jwts.parser()
					.setSigningKey(DatatypeConverter.parseBase64Binary(SysConstant.getJwtSeed()))
					.parseClaimsJws(jwt).getBody();
			return claims.getId();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("解码发生错误");
			return "";
		}

	}


	public static void main(String[] args) {

		String createJWT = createJWT("123456", "abc", "cba");
		System.out.println(createJWT);
//		System.out.println(parseJWT(createJWT));

	}


}
