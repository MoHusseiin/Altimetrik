import { Component, OnInit, OnDestroy } from '@angular/core';
import { Observable, Subscription } from 'rxjs';
import { WordService } from '../word.service';

@Component({
  selector: 'app-word-list',
  templateUrl: './word-list.component.html',
  styleUrls: ['./word-list.component.css']
})
export class WordListComponent implements OnInit, OnDestroy {

  words: string[] = [];
  words$: Observable<Object>;
  subscription: Subscription;

  constructor(private wordService: WordService) { }

  ngOnInit() {
    // this.words$ = this.wordService.getWords("", "");
    // this.subscription = this.words$.subscribe((data: any) => {
    //   console.log(JSON.stringify(data, null, 2));
    //   if (data.status === 200) {
    //     this.words = data.body;
    //   }
    // });
  }


  ngOnDestroy(): void {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }

}
