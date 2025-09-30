package com.lutfudolay.exception;

public enum MessageType {

	VALIDATION_ERROR,   // örn: eksik parametre
    DATABASE_ERROR,     // DB tarafında constraint hataları
    AUTH_ERROR,         // güvenlik / yetkilendirme hataları
    NOT_FOUND,          // entity bulunamadığında
    GENERAL_ERROR       // diğer bilinmeyen hatalar
}
