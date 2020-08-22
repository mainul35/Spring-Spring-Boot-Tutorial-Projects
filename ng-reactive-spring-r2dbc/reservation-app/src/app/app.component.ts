import { Component } from '@angular/core';
import { FormControl, FormGroup } from "@angular/forms";
import { HttpClient } from "@angular/common/http";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'reservation-app';
  constructor(private httpClient: HttpClient) {
  }

  private baseUrl: string = 'http://localhost:8080'
  private reservationUrl: string = this.baseUrl + '/room/reservation'
}
