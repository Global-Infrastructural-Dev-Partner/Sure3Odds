package com.gidp.sure3odds.service.games;

import com.gidp.sure3odds.entity.games.Comments;
import com.gidp.sure3odds.entity.games.Games;
import com.gidp.sure3odds.entity.response.BaseResponse;
import com.gidp.sure3odds.entity.users.Users;
import com.gidp.sure3odds.repository.games.CommentsRepository;
import com.gidp.sure3odds.repository.games.GamesRepository;
import com.gidp.sure3odds.repository.users.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CommentsService {

	@Autowired
	CommentsRepository commentsRepository;

	@Autowired
	GamesRepository gamesRepository;

	@Autowired
	UsersRepository usersRepository;


	public BaseResponse CreateComments(Comments comments, long userid) {
		BaseResponse response = new BaseResponse();

		Users users = usersRepository.findById(userid).get();
		comments.setUser(users);
		LocalDate cdate = LocalDate.now();
		comments.setDate(cdate);
		comments.setTime(new Date());
		Comments saved_comments = commentsRepository.save(comments);
		if(saved_comments != null) {
			response.setData(saved_comments);
			response.setDescription("New comment added to game successfully");
			response.setStatusCode(HttpServletResponse.SC_OK);
		}else {
			response.setDescription("New comment was not created.");
			response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
		}
		return response;

	}

	public BaseResponse GetCommentsByGameID(Long gameID) {
		BaseResponse response = new BaseResponse();
		Optional<Games> game  = gamesRepository.findById(gameID);
		List<Comments> comments = commentsRepository.findCommentsByGameOrderByTime(game.get());
		if (!comments.isEmpty()) {
			response.setData(comments);
			response.setDescription("Comments found successfully.");
			response.setStatusCode(HttpServletResponse.SC_OK);
		} else {
			response.setDescription("No result found.");
			response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
		}
		return response;

	}

}
