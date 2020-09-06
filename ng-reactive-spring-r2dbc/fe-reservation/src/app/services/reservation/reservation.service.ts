import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ReservationService {

  constructor(private httpClient: HttpClient) { }

  private baseUrl: string = 'http://localhost:8080';
  private reservationUrl: string = this.baseUrl + '/room/reservation';
  private httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  }

  createReservation(body: ReservationRequest): Observable<Reservation> {
    return this.httpClient.post<Reservation>(this.reservationUrl, body, this.httpOptions);
  }

  getAllReservation(): Observable<Reservation[]> {
    return this.httpClient.get<Reservation[]>(this.reservationUrl+'/all', this.httpOptions);
  }
}

export class ReservationRequest {
  roomNumber: number;
  checkIn: string;
  checkOut: string;
  price: number;

  constructor(roomNumber: number,
    checkIn: string,
    checkOut: string,
    price: number) {
    this.checkIn = checkIn;
    this.checkOut = checkOut;
    this.roomNumber = roomNumber;
    this.price = price;
  }
}

export interface Reservation {
  roomId: string;
  roomNumber: number;
  checkIn: Date;
  checkOut: Date;
  price: number;
}
