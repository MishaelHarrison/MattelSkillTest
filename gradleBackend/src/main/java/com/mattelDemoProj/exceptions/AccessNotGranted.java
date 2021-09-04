package com.mattelDemoProj.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN, reason = "Access nto given")
public class AccessNotGranted extends RuntimeException{}