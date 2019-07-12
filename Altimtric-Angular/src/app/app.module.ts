import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { WordsComponent } from './words/words.component';
import { WordBuildComponent } from './word-build/word-build.component';
import { WordListComponent } from './word-list/word-list.component';
import { HttpClientModule } from '@angular/common/http';
import { WordService } from './word.service';

@NgModule({
  declarations: [
    AppComponent,
    WordsComponent,
    WordBuildComponent,
    WordListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [WordService],
  bootstrap: [AppComponent]
})
export class AppModule { }