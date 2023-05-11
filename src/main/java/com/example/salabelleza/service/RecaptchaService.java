package com.example.salabelleza.service;

import com.github.mkopylec.recaptcha.validation.RecaptchaValidator;
import com.github.mkopylec.recaptcha.validation.ValidationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.apache.commons.lang3.StringUtils;

@Service
public class RecaptchaService {

    private final RecaptchaValidator recaptchaValidator;

    @Autowired
    public RecaptchaService(RecaptchaValidator recaptchaValidator) {
        this.recaptchaValidator = recaptchaValidator;
    }

    public boolean verifyRecaptcha(String clientResponse, String remoteIp, Model model) {
        if (StringUtils.isBlank(clientResponse)) {
            model.addAttribute("error_captcha", "The captcha response cannot be empty.");
            return false;
        }
        if (StringUtils.isBlank(remoteIp)) {
            model.addAttribute("error_captcha", "The IP address cannot be empty.");
            return false;
        }
        if (!isValidIpAddress(remoteIp)) {
            model.addAttribute("error_captcha", "Invalid IP address.");
            return false;
        }
        ValidationResult result = recaptchaValidator.validate(clientResponse, remoteIp);
        if (result.isFailure()) {
            String errorCode = String.valueOf(result.getErrorCodes().get(0));
            model.addAttribute("error_captcha", errorCode);
            return false;
        }
        return true;
    }

    private boolean isValidIpAddress(String ipAddress) {
        // Check if the IP address is valid and belongs to a range of allowed IPs.
        // You can implement this method based on your specific requirements.
        // Here's an example that checks if the IP address is a valid IPv4 address:
        return ipAddress.matches("^\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}$");
    }
}
