import { Component, OnInit } from '@angular/core';
import { DataService } from 'src/app/service/data/data.service';
import { catchError, tap } from 'rxjs/operators'

@Component({
  selector: 'app-informationpage',
  templateUrl: './informationpage.component.html',
  styleUrls: ['./informationpage.component.css']
})
export class InformationpageComponent implements OnInit {
  user: any;

  constructor(private dataService: DataService) { }

  ngOnInit(): void {
    this.dataService.getData().pipe(
      tap(user => {
        this.user = user;
        console.log(user);
      }),
      catchError(error => {
        console.error('Erreur lors de la récupération des informations utilisateur', error);
        return error; // Retourne un observable vide pour éviter de propager l'erreur
      })
    ).subscribe();
  }
}
