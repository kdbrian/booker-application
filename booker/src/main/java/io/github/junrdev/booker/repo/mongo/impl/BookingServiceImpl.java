package io.github.junrdev.booker.repo.mongo.impl;

import io.github.junrdev.booker.domain.dto.BookingDto;
import io.github.junrdev.booker.domain.enumarations.BOOKING_STATUS;
import io.github.junrdev.booker.domain.enumarations.PAYMENT_STATUS;
import io.github.junrdev.booker.domain.model.Booking;
import io.github.junrdev.booker.domain.model.Vehicle;
import io.github.junrdev.booker.domain.service.BookingService;
import io.github.junrdev.booker.repo.mongo.BookingRepository;
import io.github.junrdev.booker.repo.mongo.VehicleRepository;
import io.github.junrdev.booker.util.error.AppError;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final VehicleRepository vehicleRepository;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository, VehicleRepository vehicleRepository) {
        this.bookingRepository = bookingRepository;
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public List<Booking> getBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public List<Booking> getBookingsByPaymentStatus(PAYMENT_STATUS paymentStatus) {
        return bookingRepository.findByPaymentStatus(paymentStatus);
    }

    @Override
    public List<Booking> getBookingsByUserId(String userID) {

//        TODO: add check later after impl of user repo
//        if (!ObjectId.isValid(userID))
//            throw new AppError("Invalid user id : "+userID, HttpStatus.BAD_REQUEST);

        return bookingRepository.findByUserID(userID);
    }

    @Override
    public List<Booking> getBookingsByVehicles(Vehicle vehicle) {
        return bookingRepository.findByVehicle(vehicle);
    }

    @Override
    public Booking addBooking(BookingDto dto) {

        if (dto.getVehicleId() == null)
            throw new AppError("Missing vehicle id : ", HttpStatus.BAD_REQUEST);

        if (!ObjectId.isValid(dto.getVehicleId()))
            throw new AppError("Invalid vehicle id : " + dto.getVehicleId(), HttpStatus.BAD_REQUEST);

        Vehicle vehicle = vehicleRepository.findById(dto.getId())
                .orElseThrow(() -> new AppError("Invalid vehicle id : " + dto.getVehicleId(), HttpStatus.BAD_REQUEST));

        Booking booking = Booking.builder()
                .createdAt(dto.getCreatedAt() != null ? dto.getCreatedAt() : LocalDateTime.now().toString())
                .paymentStatus(dto.getPaymentStatus())
                .vehicle(vehicle)
                .userID(dto.getUserId())
                .build();

        return bookingRepository.save(booking);
    }

    @Override
    public Booking cancelBooking(String bookingID) {

        if (!ObjectId.isValid(bookingID))
            throw new AppError("Invalid booking id : " + bookingID, HttpStatus.BAD_REQUEST);

        Booking booking = bookingRepository.findById(bookingID)
                .orElseThrow(() -> new AppError("Missing booking id : " + bookingID, HttpStatus.BAD_REQUEST));

        booking.setBookingStatus(BOOKING_STATUS.CANCELLED);

        return bookingRepository.save(booking);
    }

    @Override
    public void deleteBooking(String bookingID) {
        if (!ObjectId.isValid(bookingID))
            throw new AppError("Invalid Booking id : " + bookingID, HttpStatus.BAD_REQUEST);

        bookingRepository.deleteById(bookingID);
    }
}
