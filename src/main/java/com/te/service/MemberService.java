
package com.te.service;

import java.util.List;
import java.util.Set;

import com.te.dao.MemberDao;
import com.te.model.Member;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

public class MemberService {

    private final MemberDao memberDao = new MemberDao();

    private final Validator validator =
        Validation.buildDefaultValidatorFactory().getValidator();

    private void validate(Member member) {
        Set<ConstraintViolation<Member>> errors =
            validator.validate(member);

        if (!errors.isEmpty()) {
            throw new IllegalArgumentException(
                errors.iterator().next().getMessage());
        }
    }

    public Member registerMember(Member member) {
        validate(member);

        if (memberDao.countByEmail(member.getEmail()) > 0) {
            throw new IllegalArgumentException(
                "Email already registered");
        }

        return memberDao.save(member);
    }

    public  Member getMemberById(Long id) {
        return memberDao.findById(id);
    }

    public List<Member> getAllMembers() {
        return memberDao.findAll();
    }

    public List<Member> searchMembers(String keyword) {
        return memberDao.searchMembers(keyword);
    }

    public Member updateMember(Member member) {
        if (member.getId() == null) {
            throw new IllegalArgumentException(
                "Member ID is required");
        }

        validate(member);

        Member existing = memberDao.findById(
            member.getId().longValue());

        if (existing == null) {
            throw new IllegalArgumentException("Member not found");
        }

        if (memberDao.countByEmailExcludingId(
                member.getEmail(), member.getId().longValue()) > 0) {
            throw new IllegalArgumentException(
                "Email already registered");
        }

        existing.setName(member.getName());
        existing.setEmail(member.getEmail());
        existing.setPhone(member.getPhone());
        existing.setAddress(member.getAddress());
        existing.setMembershipDate(member.getMembershipDate());

        return memberDao.update(existing);
    }

    public boolean deleteMember(int id) {
        return memberDao.deleteMember(id);
    }
}
