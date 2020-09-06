import { Component } from '@angular/core';
import { FormControl, FormGroup } from "@angular/forms";
import {Reservation, ReservationRequest, ReservationService} from "./services/reservation/reservation.service";
import {tap} from "rxjs/operators";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'reservation-app';
  constructor(private reservationService: ReservationService) {
  }

  rooms : Room[];
  roomSearchForm: FormGroup;
  currentCheckInVal: string;
  currentCheckOutVal: string;
  currentPriceVal: number;
  currentRoomNo: number;
  currentReservations: Reservation[];

  ngOnInit() {
    this.roomSearchForm = new FormGroup({
      checkIn: new FormControl(''),
      checkOut: new FormControl(''),
      roomNumber: new FormControl('')
    });

    this.roomSearchForm.valueChanges.subscribe(form => {
      this.currentCheckInVal = form.checkIn;
      this.currentCheckOutVal = form.checkOut;
      if (form.roomNumber) {
        let roomValues: string[] = form.roomNumber.split("|");
        this.currentRoomNo = Number(roomValues[0].trim());
        this.currentPriceVal = Number(roomValues[1].trim())
      }
    });
    this.rooms = [
      new Room('1', '111', '1000'),
      new Room('2', '112', '1200'),
      new Room('3', '113', '1000'),
    ]

    this.getCurrentReservations();
  }

  createReservation() {
    this.reservationService.createReservation(
      new ReservationRequest(this.currentRoomNo, this.currentCheckInVal, this.currentCheckOutVal, this.currentPriceVal)
    ).subscribe(value => {
      this.currentReservations.push(value)
    })
  }

  getCurrentReservations() {
    return this.reservationService.getAllReservation()
      .pipe(tap(x => console.log(x)))
      .subscribe(value => {
      this.currentReservations = value
    });
  }
}

export class Room {
  id: string;
  roomNumber: string;
  price: string;

  constructor(id: string, roomNumber: string, price: string) {
    this.id = id;
    this.roomNumber = roomNumber;
    this.price = price;

  }

}
