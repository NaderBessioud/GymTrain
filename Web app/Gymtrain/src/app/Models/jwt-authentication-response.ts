import {User} from "./user";

export class JwtAuthenticationResponse {

  user:User=new User();
  token:any;
  refreshtoken:any;
}
