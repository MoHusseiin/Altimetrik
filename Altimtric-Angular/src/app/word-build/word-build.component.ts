import { Component, OnInit , OnDestroy} from '@angular/core';
import { WordService } from '../word.service';
import { Subscription, Observable } from 'rxjs';

@Component({
  selector: 'app-word-build',
  templateUrl: './word-build.component.html',
  styleUrls: ['./word-build.component.css']
})
export class WordBuildComponent implements OnInit, OnDestroy {
  
  subscription: Subscription;
  addNewValue: string = null;

  constructor(private wordService: WordService) { }

  phase: string;
  length: string;

  words: string[] = [];

  ngOnInit() {
  }

  getLegth(event){
    this.length = event.target.value;
  }

  getPhase(event){
    this.phase = event.target.value;
  }

  onClickMe(){
    console.log(this.length+" pp "+this.phase);
    this.subscription = this.wordService.getWords(this.phase, this.length).subscribe(
      (data : any)=> {
        console.log('kk '+JSON.stringify(data));
        if (data.status === 200) {
          this.words = data.body;
        }
      }
    );
  }

  ngOnDestroy(): void {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }
}
