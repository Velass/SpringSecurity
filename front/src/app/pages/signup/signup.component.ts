import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { DataService } from 'src/app/service/data/data.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent {
constructor(private dataService : DataService){}

onSubmit(ngForm : NgForm){

  console.log(ngForm.value)
  this.dataService.createUser(ngForm.value).subscribe(res =>{
    console.log("resultat" + JSON.stringify(res));
  })
}
}
