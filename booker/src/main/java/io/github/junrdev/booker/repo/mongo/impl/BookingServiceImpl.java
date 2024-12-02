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
import io.github.junrdev.booker.util.mappers.BookingMapper;
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
    private final BookingMapper bookingMapper;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository, VehicleRepository vehicleRepository, BookingMapper bookingMapper) {
        this.bookingRepository = bookingRepository;
        this.vehicleRepository = vehicleRepository;
        this.bookingMapper = bookingMapper;
    }

    @Override
    public List<Booking> getBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking getBookingWithId(String bookingId) {
        checkId(bookingId);
        return bookingRepository.findById(bookingId)
                .orElseThrow((() -> new AppError("Missing booking id : " + bookingId, HttpStatus.NOT_FOUND)));
    }

    @Override
    public List<Booking> getBookingsByPaymentStatus(PAYMENT_STATUS paymentStatus) {
        return bookingRepository.findByPaymentStatus(paymentStatus);
    }

    @Override
    public List<Booking> getBookingsByUserPaymentStatusAndBookingStatusAndVehicle(String user, PAYMENT_STATUS paymentStatus, BOOKING_STATUS bookingStatus, String vehicleID) {
        checkId(vehicleID);
        Vehicle vehicle = vehicleRepository.findById(vehicleID)
                .orElseThrow(() -> new AppError("Missing Booking id : " + vehicleID, HttpStatus.BAD_REQUEST));

        return bookingRepository.findByUserIDAndPaymentStatusAndBookingStatusAndVehicle(user, paymentStatus, bookingStatus, vehicle);
    }

    @Override
    public List<Booking> getBookingsByUserId(String userID) {
//        TODO: add check later after impl of user repo
        return bookingRepository.findByUserID(userID);
    }

    @Override
    public List<Booking> getBookingsByBookingStatus(BOOKING_STATUS bookingStatus) {
        return bookingRepository.findByBookingStatus(bookingStatus);
    }

    @Override
    public List<Booking> getBookingsByUserIdAndBookingStatus(String userID, BOOKING_STATUS bookingStatus) {
        return bookingRepository.findByUserIDAndBookingStatus(userID, bookingStatus);
    }

    @Override
    public List<Booking> getBookingsByUserIdAndPaymentStatus(String userID, PAYMENT_STATUS paymentStatus) {
        return bookingRepository.findByUserIDAndPaymentStatus(userID, paymentStatus);
    }

    @Override
    public List<Booking> getBookingsByVehicles(String vehicleID) {
        checkId(vehicleID);
        Vehicle vehicle = vehicleRepository.findById(vehicleID)
                .orElseThrow(() -> new AppError("Missing vehicle id : " + vehicleID, HttpStatus.BAD_REQUEST));
        return bookingRepository.findByVehicle(vehicle);
    }

    @Override
    public Booking updateBooking(String bookingID, BookingDto dto) {

        checkId(bookingID);

        if (dto.getId() != null && !bookingID.equals(dto.getId()))
            throw new AppError("Invalid booking id : " + bookingID, HttpStatus.BAD_REQUEST);

        Booking booking = bookingRepository.findById(bookingID)
                .orElseThrow(() -> new AppError("Missing Booking id : " + bookingID, HttpStatus.BAD_REQUEST));


        if (dto.getPaymentStatus() != null)
            booking.setPaymentStatus(dto.getPaymentStatus());

        if (dto.getBookingStatus() != null)
            booking.setBookingStatus(dto.getBookingStatus());

        booking.setUpdatedAt(LocalDateTime.now().toString());

        return bookingRepository.save(booking);
    }

    @Override
    public Booking addBooking(BookingDto dto) {

        if (dto.getVehicleId() == null)
            throw new AppError("Missing vehicle id : ", HttpStatus.BAD_REQUEST);

        checkId(dto.getVehicleId());
        Vehicle vehicle = vehicleRepository.findById(dto.getVehicleId())
                .orElseThrow(() -> new AppError("Missing vehicle id : " + dto.getVehicleId(), HttpStatus.BAD_REQUEST));

        Booking booking = bookingMapper.fromDto(dto);
        booking.setVehicle(vehicle);
        return bookingRepository.save(booking);
    }

    @Override
    public Booking cancelBooking(String bookingID) {
        checkId(bookingID);
        Booking booking = bookingRepository.findById(bookingID)
                .orElseThrow(() -> new AppError("Missing booking id : " + bookingID, HttpStatus.BAD_REQUEST));

        if (booking.getBookingStatus() == BOOKING_STATUS.CANCELLED)
            throw new AppError("Attempt to cancel already cancelled booking", HttpStatus.BAD_REQUEST);

        booking.setBookingStatus(BOOKING_STATUS.CANCELLED);

        Vehicle vehicle = booking.getVehicle();

        if (vehicle.getSeatsOccuppied() > 0)
            vehicle.setSeatsOccuppied(vehicle.getSeatsOccuppied() - 1);

        vehicle.setUpdatedAt(LocalDateTime.now().toString());
        vehicleRepository.save(vehicle);

        return bookingRepository.save(booking);
    }

    @Override
    public void deleteBooking(String bookingID) {
        checkId(bookingID);
        bookingRepository.deleteById(bookingID);
    }

    @Override
    public Long deleteBookings() {
        long count = bookingRepository.count();
        bookingRepository.deleteAll();
        return count;
    }
}
