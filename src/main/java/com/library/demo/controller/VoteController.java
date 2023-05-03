package com.library.demo.controller;

import com.library.demo.model.Vote;
import com.library.demo.repository.VoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@AllArgsConstructor
@Controller
public class VoteController {

    private final VoteRepository voteRepository;



    @PostMapping("/booksRating/{id}")
    @Transactional
    public String addVote(HttpServletRequest request, @PathVariable(value = "id") Long id, @AuthenticationPrincipal(expression = "username") String username, String rating){
        int ratingValue = Integer.parseInt(rating);

        Vote vote = new Vote();
        vote.setBookId(id);
        vote.setUsername(username);
        vote.setValue(ratingValue);

        if(voteRepository.existsByUsernameAndBookId(username,id)){
            voteRepository.updateRatingValueByUsernameAndBookId(vote.getValue(),vote.getUsername(),vote.getBookId());
        }
        else{
            voteRepository.save(vote);
        }


        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }
}
