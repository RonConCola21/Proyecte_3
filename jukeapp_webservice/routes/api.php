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
Route::post('/addToken',[UserController::class, 'addToken']);
//Token
Route::get('/token/{value}',[TokenController::class,'getToken']);
Route::post('/deletetoken',[TokenController::class,'removeToken']);
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
Route::get('/getToken2/{value}',[SpotifyController::class,'getToken2']);
Route::get('/spotifytoken',[SpotifyController::class,'getToken']);
Route::get('/searchSong/{song}',[SpotifyController::class, 'searchSong']);
Route::get('/getQueue',[SpotifyController::class, 'getQueue']);
Route::get('/getHistory',[SpotifyController::class, 'getHistory']);
Route::get('/getCurrentlyPlayingSong',[SpotifyController::class, 'getCurrentlyPlayingSong']);
Route::get('/getAuth',[SpotifyController::class, 'getAuthorization']);
Route::post('/addSongQueue',[SpotifyController::class, 'addSongQueue']);
Route::post('/removeSongQueue',[SpotifyController::class, 'removeSongQueue']);
Route::get('/getPlaylist',[SpotifyController::class, 'getPlaylistItems']);
Route::post('/updatePlaylistItems',[SpotifyController::class, 'updatePlaylistItems']);
Route::get('/getCode',[SpotifyController::class, 'getCode']);
Route::post('/skipNext',[SpotifyController::class, 'skipNext']);
Route::post('/skipPrevious',[SpotifyController::class, 'skipPrevious']);
Route::put('/resume',[SpotifyController::class, 'resume']);
Route::put('/pause',[SpotifyController::class, 'pause']);
Route::get('/returnTime',[SpotifyController::class, 'returnTime']);
Route::get('/getDevice',[SpotifyController::class, 'getDevice']);

