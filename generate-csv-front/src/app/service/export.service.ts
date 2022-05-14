import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../environments/environment";
import {Excel} from "../excel";

@Injectable({
  providedIn: 'root'
})
export class ExportService {

  baseUrl = environment.baseUrl;

  constructor(private http: HttpClient) {
  }

  export(excel: Excel): Observable<Blob> {
    return this.http.post(this.baseUrl + '/export/', excel, {responseType: 'blob'}) as Observable<Blob>;
  }

  downLoadBlob(response: Blob): void {
    const blob = new Blob([response], {type: 'application/vnd.ms-excel'});
    const data = window.URL.createObjectURL(blob);
    const link = document.createElement('a');
    link.href = data;
    let possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890,./;'[]\=-)(*&^%$#@!~`";
    const lengthOfCode = 40;
    link.download = 'Excel_' + this.makeRandom(lengthOfCode, possible);
    link.dispatchEvent(new MouseEvent('click', {bubbles: true, cancelable: true, view: window}));
    window.URL.revokeObjectURL(data);
    link.remove();
  }

  private makeRandom(lengthOfCode: number, possible: string) {
    let text = "";
    for (let i = 0; i < lengthOfCode; i++) {
      text += possible.charAt(Math.floor(Math.random() * possible.length));
    }
    return text;
  }

}
