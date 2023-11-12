import {Component, ElementRef, OnInit, Renderer2, ViewChild} from '@angular/core';

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.scss']
})
export class ContactComponent implements OnInit {
  @ViewChild('myForm', { static: true }) myForm: any;
  name:string=""
  email:string=""
  message:string=""
  good:boolean=false
  constructor(private renderer: Renderer2) { }

  ngOnInit(): void {
  }

  ShowAlert(): void {
    console.log(this.name)
    if(this.name != "" && this.email!="" && this.message!="")
    this.good=true
    setTimeout(() => {
      this.onSubmit();
    }, 7000);
  }

  onSubmit() {
    this.good=false
    const formElement: HTMLFormElement = this.myForm.nativeElement;

    // Trigger the form submission
    this.renderer.setProperty(formElement, 'action', 'https://formsubmit.co/nader.bessioud@esprit.tn');
    this.renderer.setProperty(formElement, 'method', 'POST');
    formElement.submit();
  }

  closeAlert(){
    this.good=false
  }

}
