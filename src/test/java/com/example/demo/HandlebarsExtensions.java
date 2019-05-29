package com.example.demo;

import java.util.Arrays;
import java.util.List;

import org.springframework.cloud.contract.verifier.dsl.wiremock.WireMockExtensions;

import com.github.tomakehurst.wiremock.extension.Extension;
import com.github.tomakehurst.wiremock.extension.responsetemplating.ResponseTemplateTransformer;

public class HandlebarsExtensions implements WireMockExtensions {

	@Override
	public List<Extension> extensions() {
		return Arrays.asList(new ResponseTemplateTransformer(false));
	}

}
