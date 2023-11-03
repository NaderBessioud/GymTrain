import { Component, OnInit } from '@angular/core';
import {MatDialog, MatDialogRef} from "@angular/material/dialog";
import {AuthenticationComponent} from "../authentication/authentication.component";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  constructor(public dialog: MatDialog) {
  }



  ngOnInit(): void {
  }

  openModal() {
    if (this.dialog.openDialogs.length > 0) {

      this.dialog.closeAll();
      const dialogRef = this.dialog.open(AuthenticationComponent, {

        width:"1000px",
        height:"65vh",
        panelClass: 'custom-dialog-panel',


      },);

      dialogRef.afterClosed().subscribe(result => {
        console.log(`Modal result: ${result}`);
      });

    } else {
      const dialogRef = this.dialog.open(AuthenticationComponent, {

        width:"1000px",
        height:"65vh",
        panelClass: 'custom-dialog-panel'

      });

      dialogRef.afterClosed().subscribe(result => {
        console.log(`Modal result: ${result}`);
      });
    }



  }
}
