import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { UserStorageService } from 'src/app/auth/auth-services/storage-service/user-storage.service';

const BASIC_URL = environment['BASIC_URL'];

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private http: HttpClient) { }

  addUser(userDto: any): Observable<any> {
    return this.http.post(`${BASIC_URL}api/admin/user`, userDto, {
      headers: this.createAuthorizationHeader(),
    })
  }

  getAllUsers(): Observable<any> {
    return this.http.get<[]>(`${BASIC_URL}api/admin/users`, {
      headers: this.createAuthorizationHeader(),
    }).pipe(
      tap((_) => this.log('Users fetched successfully')),
      catchError(this.handleError<[]>('Error getting users', []))
    );
  }
  
  getUserById(userId: any): Observable<any> {
    return this.http.get<[]>(`${BASIC_URL}api/admin/user/${userId}`, {
      headers: this.createAuthorizationHeader(),
    }).pipe(
      tap((_) => this.log('User Fetched successfully')),
      catchError(this.handleError<[]>('Error Getting User', []))
    );
  }

  deleteUserById(userId: any): Observable<any> {
    return this.http.delete<[]>(`${BASIC_URL}api/admin/user/${userId}`, {
      headers: this.createAuthorizationHeader(),
    }).pipe(
      tap((_) => this.log('User Deleted successfully')),
      catchError(this.handleError<[]>('Error Deleting user', []))
    );
  }

  private createAuthorizationHeader(): HttpHeaders {
    return new HttpHeaders().set(
      'Authorization',
      'Bearer ' + UserStorageService.getToken()
    );
  }

  private log(message: string): void {
    console.log(`Admin Service: ${message}`);
  }

  private handleError<T>(operation = 'operation', result?: T): any {
    return (error: any): Observable<T> => {
      console.error(error);
      this.log(`${operation} failed: ${error.message}`);
      return of(result as T);
    };
  }
}
