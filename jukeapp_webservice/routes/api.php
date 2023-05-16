<?php

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;
use App\Http\Controllers\SongController;
use App\Http\Controllers\UserController;
use App\Http\Controllers\TokenController;
use App\Http\Controllers\SpotifyController;
use App\Http\Controllers\PetitionController;
use App\Http\Controllers\QueueController;
use App\Http\Controllers\PetitionQueueController;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/
//BaseDeDades
//Song
Route::get('/song', [SongController::class, 'getSongs']);
Route::get('/song/{tipus}',[SongController::class, 'getSongs2']);
Route::post('/createSong',[SongController::class, 'createSong']);
Route::post('/updateSong',[SongController::class, 'updateSong']);
//Users
Route::get('/users',[UserController::class, 'getUsers']);
Route::post('/user',[UserController::class, 'getUser']);
Route::post('/createUser',[UserController::class, 'setUser']);
Route::post('/updateUser',[UserController::class, 'updateUser']);
//Token
Route::get('/token/{value}',[TokenController::class,'getToken']);
Route::post('/deletetoken/{value}',[TokenController::class,'removeToken']);
//Petition
Route::get('/getPetitions',[PetitionController::class, 'getPetitions']);
Route::post('/createPetition/{value}',[PetitionController::class, 'createPetition']);
Route::post('/removePetition',[PetitionController::class, 'removePetition']);
//Queue
Route::post('/createQueue',[QueueController::class, 'createQueue']);
Route::get('/getQueue',[QueueController::class, 'getQueue']);
//PetitionQueue
Route::get('/getPetitionsQueue',[PetitionQueueController::class, 'getPetitionsQueue']);
Route::post('/createPetitionQueue',[PetitionQueueController::class, 'createPetitionQueue']);
Route::post('/removePetitionQueue',[PetitionQueueController::class, 'removePetitionQueue']);
//Spotify
Route::get('/spotifytoken',[SpotifyController::class,'getToken']);
Route::get('/searchSong/{song}',[SpotifyController::class, 'searchSong']);






