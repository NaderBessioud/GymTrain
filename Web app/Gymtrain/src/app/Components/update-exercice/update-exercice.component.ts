import {Component, Inject, OnInit, ViewChild} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {UserService} from "../../Services/user.service";
import {Exercice} from "../../Models/exercice";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";

@Component({
  selector: 'app-update-exercice',
  templateUrl: './update-exercice.component.html',
  styleUrls: ['./update-exercice.component.scss']
})
export class UpdateExerciceComponent implements OnInit {
  exerciceForm: FormGroup=new FormBuilder().group({});
  exercice:Exercice=new Exercice();

  constructor(private service:UserService,private fb: FormBuilder,public dialogRef: MatDialogRef<UpdateExerciceComponent>,
              @Inject(MAT_DIALOG_DATA) public id: any) { }

  ngOnInit(): void {

    this.exerciceForm = this.fb.group({


      sets: ['', Validators.required],
      rep: ['', Validators.required],
      resistance: ['', Validators.required],

    });

    this.service.getExerciceById(this.id).subscribe(ex=>{
      this.exercice=ex
    })
  }

  updateExercice(){
    this.service.updateExercice(this.exercice).subscribe(ex=>{
      if(ex){

      }
    })
  }

  closeModal(){
    this.dialogRef.close()
  }

}


