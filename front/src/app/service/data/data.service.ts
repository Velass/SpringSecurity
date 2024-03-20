import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router'; // Importez le service Router ici
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class DataService {
  private apiUrl = 'http://localhost:8080/api/user'; // Remplacez cela par votre API URL

  constructor(private http: HttpClient, private router: Router) { } // Injectez le service Router ici

  // Méthode GET
  getData(): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}`);
  }

  // Méthode POST
  createUser(data: any): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/create`, data);
  }

  // Méthode POST
  login(login: { address: string, password: string }): Observable<any> {
    const loginUrl = `${this.apiUrl}/login`;
    return this.http.post(loginUrl, login, { withCredentials: true });
  }

  checkAuthenticated(): boolean {
    const cookies = document.cookie;
    if (!cookies.includes('votre_cookie')) {
      this.router.navigate(['/login']);
      return false;
    }
    return true;
  }
}
