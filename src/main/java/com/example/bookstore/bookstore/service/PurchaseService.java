package com.example.bookstore.bookstore.service;

import com.example.bookstore.bookstore.dto.request.PurchaseRequestDto;
import com.example.bookstore.bookstore.dto.response.PurchaseResponseDto;
import com.example.bookstore.bookstore.dto.type_enum.*;
import com.example.bookstore.bookstore.model.*;
import com.example.bookstore.bookstore.repository.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final PointUsageRepository pointUsageRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public PurchaseResponseDto createPurchase(PurchaseRequestDto request) {
        UserModel user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        BookModel book = bookRepository.findById(request.getBookId())
                .orElseThrow(() -> new EntityNotFoundException("Book not found"));

        double total = book.getBasePrice() * request.getQuantity();

        PurchaseModel saved = purchaseRepository.save(PurchaseModel.builder()
                .user(user)
                .book(book)
                .quantity(request.getQuantity())
                .totalPrice(total)
                .build());

        Map<BookType, Integer> pointMap = Map.of(
                BookType.NEW_RELEASE, 10,
                BookType.REGULAR, 5,
                BookType.OLD_EDITION, 2
        );

        int pointEarned = request.getQuantity() * pointMap.getOrDefault(book.getType(), 0);

        pointUsageRepository.save(PointUsageModel.builder()
                .user(user)
                .pointsUsed(pointEarned)
                .reason("EARN")
                .build());

        return PurchaseResponseDto.builder()
                .id(saved.getId())
                .bookTitle(book.getTitle())
                .bookType(book.getType())
                .quantity(saved.getQuantity())
                .totalPrice(saved.getTotalPrice())
                .purchasedAt(saved.getPurchasedAt())
                .build();
    }


    public List<PurchaseResponseDto> getPurchasesByUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        UserModel user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        return purchaseRepository.findAll().stream()
                .map(p -> PurchaseResponseDto.builder()
                        .id(p.getId())
                        .bookTitle(p.getBook().getTitle())
                        .bookType(p.getBook().getType())
                        .quantity(p.getQuantity())
                        .totalPrice(p.getTotalPrice())
                        .purchasedAt(p.getPurchasedAt())
                        .build())
                .toList();
    }

    public int getTotalLoyaltyPoints(String email) {
        UserModel user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        List<PointUsageModel> usages = pointUsageRepository.findByUserId(user.getId());

        int earned = usages.stream()
                .filter(u -> "EARN".equals(u.getReason()))
                .mapToInt(PointUsageModel::getPointsUsed)
                .sum();

        int used = usages.stream()
                .filter(u -> !"EARN".equals(u.getReason()))
                .mapToInt(PointUsageModel::getPointsUsed)
                .sum();

        return earned - used;
    }

}
