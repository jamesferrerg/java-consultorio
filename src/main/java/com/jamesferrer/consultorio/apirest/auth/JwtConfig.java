package com.jamesferrer.consultorio.apirest.auth;

public class JwtConfig {

	//public static final String LLAVE_SECRETA = "consultorio.nuevas.sonrisas.$2019*";
	
	public static final String RSA_PRIVADA = "-----BEGIN RSA PRIVATE KEY-----\n" + 
			"MIIEpAIBAAKCAQEAoONtj/b7217Nnusoqh2LyqQJLo319VZS15JOImfEM9tmxqL/\n" + 
			"tU5K1p1yNE2J/696WcWwGBjGJdo14wg6ClMbI9r8osbEE2hzz8D5RlQ3EZ3xy/L4\n" + 
			"8tfFXl9gTikj66P3EzDlpgxFJexVTpj+mKUB9hM7utoTFhadSFNIifQh9tm6MhNL\n" + 
			"azPQpMn/n0YLrU+OyCOTbK4ZV5Ixl7rKyUph+rpaVsoejn+9+J+TSlMPEqEahRBd\n" + 
			"bXGslFUq/UfWV47mL+Dt7t92TL4vZbmhYBXYky245jHgyUe8042Ps1yNdeGYOTmB\n" + 
			"vKPg9YnHT/UvJRUTvAK4QGi89empqGKtAvKx3wIDAQABAoIBAGNACX2vQm1wzW4E\n" + 
			"6aAP/AW1LcjfYGTJrO85XSQSWVIYPg25zf8d3kMI+8PSNZhauyKYNmqXFd2SAH4e\n" + 
			"vBdUb0CuPf14msrSO+dkxyIgn5wpkFldeT/t5d6y/akZ7gPWsXvQRUsq9JlRcsq6\n" + 
			"zQyDdnNEKCu7jdypdLuCTRR1W2J4sd0qpLNT9C3eXPMGWMJU1BP4MRF9LqIp1IXz\n" + 
			"OwbxDQOT2lKXnWQGHZXvvAu/o08MUaZUk3koDq6m8HGzyxZ0tELyLzli3mgv4vQr\n" + 
			"PF7gWmPQluUBuBMJEw+sGENELN1LmHmh0ntLdto9qXgefvaOsvPsaFW1Flpz3N/E\n" + 
			"anU7oVkCgYEAzf/K2cORJ+h5OmYTIfzXJfpycxP6KYflUyk7gq7iN8MyQCvgWAiB\n" + 
			"HGvOvAh58bqNiVwvcZvncY8KTntt21BS6eIthjJtiRX75Rp1fQrfACt+sxmVr8IA\n" + 
			"E5cy5gOAWNVrtl0fuwD6QJsMT4QvMO47402tpwrEQzFDUtedSf+9BM0CgYEAx/CT\n" + 
			"+VUpgQp2dC00OzXvM/7B7nU/EIvCvE2VpZBNtbCaHh20cL6x/wsDJKA+F5I4U6Or\n" + 
			"RUerBOVUl9nFYjVDemwEGaum2F5JzwVDhJlRTjGGhKPO/+sWqar+Zf0B8Ap9BryF\n" + 
			"S2B8uYAEYzlzpMksJIQx2LUkirC/I8zbITYx8VsCgYBkHAAPrfvX4iEv4TVBGifQ\n" + 
			"fBKsnDPBhRpMPMh/7M9g/ApqpygCgDeGgVnZhWEFuXxZGaSw2Gw0gqgmldNAdpuk\n" + 
			"r9ch1VXn/fgJUb9xAhx3b6vGDBPzcxsoah1BLMht42HSIRVvgr0bMn5cEkgAxnXy\n" + 
			"M+jFOfVBFiq6krAMqyzHrQKBgQC2raoVymjUfSHDfjXVz66RfDfmzvti+79eWyja\n" + 
			"j+Fgm04FLDDcPm/VTlN92GdDFBcg3E61SwkHvR0wbQm6dF4F8cFBTyNvV57qZq3M\n" + 
			"7mRqJDf64sxT6ZDYTuKHfsaRcpPPXtoPB8aNmljn6+ssaZ5Kcvo0PTV4/op1hEvG\n" + 
			"qYSyNwKBgQCoKmg3+qOR0vaw/Rk8qErkeWBb2OoADE1HTgnEMiyohcCoYu0Zid4H\n" + 
			"C4x8JjXk+kNfgAOS6IA9YfKT7Gk1a6dXKIOHC4z7RdClr0RA4O4ISDVCqPipAfLR\n" + 
			"jQ1/1wsoXbhpNTlUTbkbVWp8nbMGGqS+Ofe1ePi3wMnAprnZTnf5Gg==\n" + 
			"-----END RSA PRIVATE KEY-----";
	public static final String RSA_PUBLICA = "-----BEGIN PUBLIC KEY-----\n" + 
			"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAoONtj/b7217Nnusoqh2L\n" + 
			"yqQJLo319VZS15JOImfEM9tmxqL/tU5K1p1yNE2J/696WcWwGBjGJdo14wg6ClMb\n" + 
			"I9r8osbEE2hzz8D5RlQ3EZ3xy/L48tfFXl9gTikj66P3EzDlpgxFJexVTpj+mKUB\n" + 
			"9hM7utoTFhadSFNIifQh9tm6MhNLazPQpMn/n0YLrU+OyCOTbK4ZV5Ixl7rKyUph\n" + 
			"+rpaVsoejn+9+J+TSlMPEqEahRBdbXGslFUq/UfWV47mL+Dt7t92TL4vZbmhYBXY\n" + 
			"ky245jHgyUe8042Ps1yNdeGYOTmBvKPg9YnHT/UvJRUTvAK4QGi89empqGKtAvKx\n" + 
			"3wIDAQAB\n" + 
			"-----END PUBLIC KEY-----";
}
