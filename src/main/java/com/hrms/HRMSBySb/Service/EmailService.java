package com.hrms.HRMSBySb.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import com.hrms.HRMSBySb.Entity.JobSeeker;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;

    public void sendEmail(JobSeeker jobSeeker, String status,String fromEmail) {
        Context context = new Context();
        context.setVariable("empName", jobSeeker.getEmpName());
        context.setVariable("jobTitle", jobSeeker.getCareer().getTitle());
        context.setVariable("department", jobSeeker.getDepartment());
        context.setVariable("status", status.toUpperCase());

        String body = templateEngine.process("email-template", context);

        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(fromEmail);
            helper.setTo(jobSeeker.getEmail()); 
            helper.setSubject("Candidate " + status + ": " + jobSeeker.getEmpName());
            helper.setText(body, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
