import { Injectable, EventEmitter } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class WordService {
  onTaskAdd = new EventEmitter();

  constructor(private http: HttpClient) { }

  getWords(phrase: string, length: string) :Observable<Object>{
    console.log(phrase+ " "+length);
    return this.http.get(`http://localhost:8080/wordbuilder?phrase=${phrase}&length=${length}`, {observe: 'response'});
  }
}
