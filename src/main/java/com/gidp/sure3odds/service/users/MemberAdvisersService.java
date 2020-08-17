package com.gidp.sure3odds.service.users;

import com.gidp.sure3odds.entity.response.BaseResponse;
import com.gidp.sure3odds.entity.users.MemberAdvisers;
import com.gidp.sure3odds.entity.users.Users;
import com.gidp.sure3odds.repository.users.MemberAdvisersRepository;
import com.gidp.sure3odds.repository.users.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@Service
public class MemberAdvisersService {

	@Autowired
	MemberAdvisersRepository memberAdvisersRepository;

	@Autowired 
	UsersRepository usersRepository;
	
	public BaseResponse AssignMemberAdviser(MemberAdvisers memberAdvisers) {
		BaseResponse response = new BaseResponse();
		MemberAdvisers saved_memberAdvisers = memberAdvisersRepository.save(memberAdvisers);
		if (saved_memberAdvisers != null) {
			Long savedMemberAdviserID = saved_memberAdvisers.getId();
			UpdateMemberDetails(savedMemberAdviserID, "Assigned");
			UpdateAdviserDetails(savedMemberAdviserID, "Assigned");
			response.setData(saved_memberAdvisers);
			response.setDescription("Member has been assigned an Adviser successfully.");
			response.setStatusCode(HttpServletResponse.SC_OK);
		}else {
			response.setDescription("Adviser could not be assigned to the member");
			response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
		}

		return response;
	}
	
	
	public String UpdateMemberDetails(Long memberAdvisersid, String newValue) {
		String result = "failed";
		Optional<MemberAdvisers> memberAdvisers = memberAdvisersRepository.findById(memberAdvisersid);
		long memberUserID = memberAdvisers.get().getMemberUserID().getId();
		Users user = usersRepository.findById(memberUserID).get();
		user.setAssigned(newValue);
		Users updated_user = usersRepository.save(user);
		if(updated_user != null) {
			result = "success";
		}
		return result;
	}
	
	public String UpdateAdviserDetails(Long memberAdvisersid, String newValue) {
		String result = "failed";
		Optional<MemberAdvisers> memberAdvisers = memberAdvisersRepository.findById(memberAdvisersid);
		long adviserUserID = memberAdvisers.get().getAdviserUserID().getId();
		Users user = usersRepository.findById(adviserUserID).get();
		user.setAssigned(newValue);
		Users updated_user = usersRepository.save(user);
		if(updated_user != null) {
			result = "success";
		}
		return result;
	}
	
	
	public BaseResponse DeleteMemberAdviser(long ID) {
		BaseResponse response = new BaseResponse();
		Optional<MemberAdvisers> memberAdvisers = memberAdvisersRepository.findById(ID);
		if(memberAdvisers.isPresent()) {
			UpdateMemberDetails(ID, "Pending");
			UpdateAdviserDetails(ID, "Pending");
			memberAdvisersRepository.deleteById(ID);
			response.setDescription("Adviser has been removed from the Member successfully.");
			response.setStatusCode(HttpServletResponse.SC_OK);
		}else {
			response.setDescription("No record found");
			response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
		}
		return response;

	}
	
	public BaseResponse UpdateMemberAdviser(MemberAdvisers memberAdvisers) {
		BaseResponse response = new BaseResponse();
		Long oldmemberAdvisers = memberAdvisers.getId();
		UpdateAdviserDetails(oldmemberAdvisers, "Pending");
		MemberAdvisers updated_memberAdvisers = memberAdvisersRepository.save(memberAdvisers);
		if (updated_memberAdvisers != null) {
			Long savedMemberAdviserID = updated_memberAdvisers.getId();
			UpdateMemberDetails(savedMemberAdviserID, "Assigned");
			UpdateAdviserDetails(savedMemberAdviserID, "Assigned");
			response.setData(updated_memberAdvisers);
			response.setDescription("Plan type has been updated succesfully.");
			response.setStatusCode(HttpServletResponse.SC_OK);
		} else {
			response.setDescription("Plan type was not updated.");
			response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
		}
		return response;
	}
	
	
	public BaseResponse GetMembersByAdviserUserID(Long adviserUserID) {
		BaseResponse response = new BaseResponse();
		List<MemberAdvisers> memberAdvisers  = memberAdvisersRepository.findMembersByAdviserUserID(adviserUserID);
		if (memberAdvisers != null) {
			response.setData(memberAdvisers);
			response.setDescription("Adviser found succesfully.");
			response.setStatusCode(HttpServletResponse.SC_OK);
		} else {
			response.setDescription("No result found.");
			response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
		}
		return response;

	}
	public BaseResponse GetAdviserDetailsByMemberID(Long memberUserID) {
		BaseResponse response = new BaseResponse();
		MemberAdvisers memberAdvisers  = memberAdvisersRepository.findAdviserByMemberUserID(memberUserID);
		if (memberAdvisers != null) {
			Long savedMemberAdviserID = memberAdvisers.getAdviserUserID().getId();
			Optional<Users> adviserDetails = usersRepository.findById(savedMemberAdviserID);
			response.setData(adviserDetails);
			response.setDescription("Adviser found succesfully.");
			response.setStatusCode(HttpServletResponse.SC_OK);
		} else {
			response.setDescription("No result found.");
			response.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
		}
		return response;
		
	}
	
	
}
