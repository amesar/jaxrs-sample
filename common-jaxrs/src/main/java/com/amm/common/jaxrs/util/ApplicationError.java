package com.amm.common.jaxrs.util;

/**
 * Common application error codes. 
 * @see com.amm.common.jaxrs.data.Error#getCode().
 */
public enum ApplicationError {
	Unknown,
	NotFound,
	MissingQueryParameters,
    InvalidFormat,
	IllegalSyntax,
	EmptyRequestBody,
	GenericClientError,
	GenericServerError
}
