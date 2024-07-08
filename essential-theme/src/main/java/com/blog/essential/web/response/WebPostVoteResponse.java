package com.blog.essential.web.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigInteger;

@Getter
@AllArgsConstructor
public class WebPostVoteResponse {
    private final BigInteger totalVote;
}
