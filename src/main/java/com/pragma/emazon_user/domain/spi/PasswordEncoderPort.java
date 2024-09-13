package com.pragma.emazon_user.domain.spi;

public interface PasswordEncoderPort {

    String encode(String password);

}
