import {Directive, ElementRef, HostListener, Input} from '@angular/core';

@Directive({
  selector: '[appPhoneNumber]'
})
export class PhoneNumberDirective {
  @Input('appPhoneNumber') separator: string = '-';


  constructor(private el: ElementRef) {}


  @HostListener('input', ['$event'])
  onInput(event: InputEvent) {
    const input = this.el.nativeElement as HTMLInputElement;
    const value = input.value.replace(/\D/g, '');
    const formattedValue = this.formatPhoneNumber(value);
    input.value = formattedValue;

    const cursorPosition = input.selectionStart || 0;

    if (cursorPosition < formattedValue.length) {
      input.setSelectionRange(cursorPosition, cursorPosition);
    } else {
      input.setSelectionRange(formattedValue.length, formattedValue.length);
    }
  }

  private formatPhoneNumber(value: string): string {
    let formattedValue = value.slice(0, 2) + this.separator;
    if (value.length > 2) {
      formattedValue += value.slice(2, 5) + this.separator;
    }
    if (value.length > 5) {
      formattedValue += value.slice(5, 8);
    }
    return formattedValue;
  }

}
