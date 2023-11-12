import { Component, OnInit } from '@angular/core';
import {MatDialog, MatDialogRef} from "@angular/material/dialog";
import {AuthenticationComponent} from "../authentication/authentication.component";
import {Router} from "@angular/router";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  cause:string="login"
  good:boolean=false
  dismissible = true;
  alert:any =
    {
      type: 'success',
      msg: `You successfully read this important alert message.`
    }
  constructor(public dialog: MatDialog,private router:Router) {
    const sourceComponent = this.router.getCurrentNavigation()?.extras.state?.['source'];
    console.log(sourceComponent)
    if(sourceComponent=="inside"){
      this.alert.msg="You need to be authenticated to access this page"
      this.alert.type="danger"
      this.good=true

    }
    else if(sourceComponent=="unauthorized"){
      this.alert.msg="Your are not authorized to access this page"
      this.alert.type="danger"
      this.good=true

    }
    else if(sourceComponent=="tokenexpired"){
      this.alert.msg="Your session ended. Please login again"
      this.alert.type="danger"
      this.good=true

      this.cause="tokenexpired"

    }
    else if(sourceComponent=="signout"){
      this.alert.msg="See you soon"
      this.alert.type="info"
      this.good=true

    }
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
        data:this.cause

      },);

      dialogRef.afterClosed().subscribe(result => {
        console.log(`Modal result: ${result}`);
      });

    } else {
      const dialogRef = this.dialog.open(AuthenticationComponent, {

        width:"1000px",
        height:"65vh",
        panelClass: 'custom-dialog-panel',
        data:this.cause

      });

      dialogRef.afterClosed().subscribe(result => {
        console.log(`Modal result: ${result}`);
      });
    }



  }

  closeAlert(){
    this.good=false
  }
}
