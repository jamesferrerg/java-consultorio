# java-consultorio

Para generar llave publica y privada de jwt, desde consola:

openssl genrsa -out jwt.pem

openssl rsa -in jwt.pem

openssl rsa -in jwt.pem -pubout
