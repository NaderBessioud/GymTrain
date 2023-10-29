import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {AuthenticationService} from "../../Services/authentication.service";
import {User} from "../../Models/user";
import {AbstractControl, FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {FloatLabelType} from "@angular/material/form-field";

@Component({
  selector: 'app-authentication',
  templateUrl: './authentication.component.html',
  styleUrls: ['./authentication.component.scss']
})
export class AuthenticationComponent implements OnInit {
  floatLabelControl = new FormControl('auto' as FloatLabelType);
  emailFormControl = new FormControl('', [Validators.required, Validators.email]);
  form: FormGroup=new FormBuilder().group({});
  formR: FormGroup=new FormBuilder().group({});
  user:User = new  User();
  email:string="";
  password :string = ""
  isButtonDisabled = false
  avatarImage: string = 'assets/images/gymlogin.png'; // Set the initial image
  isLoginTab: boolean = true; // Track which tab is active
  visiblePass:boolean=false;
  matchPass:boolean=false;
  PassMatch:string="2 password does not match";
  pass:string = ""


  constructor(private authservice:AuthenticationService, private fb:FormBuilder) { }




  switchToLogin() {
    this.avatarImage = this.isLoginTab ?'assets/images/gymregister.png' : 'assets/images/gymlogin.png';
    this.isLoginTab = true;


  }

  switchToRegistration() {

    this.avatarImage = this.isLoginTab ?   'assets/images/gymregister.png' : 'assets/images/gymlogin.png';
    this.isLoginTab = false;

  }


  Login(){
    this.authservice.Login(this.email, this.password).subscribe(res=>{
      if(res != null){
        sessionStorage.setItem("token",res.token);
        sessionStorage.setItem("reftoken",res.refreshtoken);
        sessionStorage.setItem("email",res.user.email);
        this.email = ""
        this.password = ""
      }
      else{
        alert("Invalid email or password")
      }

    })
  }

  Register(){
    this.authservice.Register(this.user).subscribe(u=>{
      sessionStorage.setItem("email",u.email);
    })
  }

  checkemail(){
    console.log("its work")
this.authservice.Checkemail(this.user.email).subscribe(e=>{
  console.log(e)
  if(e){
    alert("Email already exist")
    this.isButtonDisabled = true
  }
  else{
    this.isButtonDisabled = false
  }
})
  }

  checkPass(){
    if(this.user.password ==this.pass){
      this.visiblePass=false
      this.matchPass=false;
      this.isButtonDisabled=false;
    }
    else{
      this.visiblePass=true;
      this.matchPass=true;
      this.isButtonDisabled=true;
    }
  }

  ngOnInit(): void {

    this.form = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required,
        Validators.minLength(8),
        Validators.maxLength(100)]
      ]

    })

    this.formR = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required,
        Validators.minLength(8),
        Validators.maxLength(100)]
      ],
      confirmpassword: ['', [Validators.required,

        Validators.minLength(8),
        Validators.maxLength(100)]
    ]
    })

  }
  getFloatLabelValue(): FloatLabelType {
    return this.floatLabelControl.value || 'auto';
  }




}
