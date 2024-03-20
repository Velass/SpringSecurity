// login.component.ts

import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { DataService } from 'src/app/service/data/data.service';
import { AuthService } from 'src/app/service/auth/auth.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private dataService: DataService, private router: Router) { } 

  user = {
    email: '',
    password: ''
  };

  ngOnInit(): void {
   // Vérifiez si l'utilisateur est déjà authentifié lors de l'initialisation du composant
    if (this.dataService.checkAuthenticated()) {
      // Redirigez vers la page d'information si l'utilisateur est authentifié
      this.router.navigate(['/information']);
    }
  }

  onSubmit(ngForm: NgForm) {
    console.log(ngForm.value);
    this.dataService.login(ngForm.value).subscribe(res => {
      console.log(res);
      // Rediriger vers la page d'informations après une connexion réussie
      this.router.navigate(['/information']);
    });
  }
}
