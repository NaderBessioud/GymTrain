import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {Nutrition} from "../../Models/nutrition";

@Component({
  selector: 'app-nutrition-details',
  templateUrl: './nutrition-details.component.html',
  styleUrls: ['./nutrition-details.component.scss']
})
export class NutritionDetailsComponent implements OnInit {

  constructor(@Inject(MAT_DIALOG_DATA) public UserNutrition: Nutrition,
              public dialogRef: MatDialogRef<NutritionDetailsComponent>) { }

  ngOnInit(): void {

  }

  closeModal(){
    this.dialogRef.close()
  }

}
