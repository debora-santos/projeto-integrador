import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  private baseUrl = 'localhost:8080/api/v1/usuarios';

  constructor(private http: HttpClient) { }

  getUsuarios(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }

  getUsuario(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  // tslint:disable-next-line:ban-types
  createUsuario(usuario: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}`, usuario);
  }

  // tslint:disable-next-line:ban-types
  updateUsuario(id: number, value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl}/${id}`, value);
  }

  deletePessoa(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`,
      { responseType: 'text'});
  }

}
