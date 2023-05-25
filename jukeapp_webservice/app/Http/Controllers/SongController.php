<?php

namespace App\Http\Controllers;
use Illuminate\Http\Request;
use App\Http\Controllers\Controller;
use App\Models\Song;
use Illuminate\Support\Facades\Hash;
use Tymon\JWTAuth\Facades\JWTAuth;
use App\Models\User;
use App\Models\Petition;
use App\Models\Petition_queue;
use DateTime;
use App\Models\SpotifyToken;
use Illuminate\Support\Facades\Http;

class SongController extends Controller{
    public function getSongs(){
        try{
            $data = Song::all();
        } catch (\Illuminate\Database\QueryException $ex){
            return response()->json([
                'database_error' => "Database error: ".$ex
            ], 400);
        }catch(\Throwable $ex){
            return response()->json([
                'error' => "General error: ".$ex
            ], 400);
        }

        return response()->json(compact('data'));
    }

    public function getSongs2($tipus){
        try{
            $data = Song::where('son_status','=',$tipus)->get();
        } catch (\Illuminate\Database\QueryException $ex){
            return response()->json([
                'database_error' => "Database error: ".$ex
            ], 400);
        }catch(\Throwable $ex){
            return response()->json([
                'error' => "General error: ".$ex
            ], 400);
        }

        return response()->json(compact('data'));
    }

    public function createSong(Request $request){
        $son_spotify_id = $request->son_spotify_id;
        $son_img = $request->son_img;
        $son_name = $request->son_name;
        $son_artist1 = $request->son_artist1;
        $son_artist2 = $request->son_artist2;
        $son_duration = $request->son_duration;
        $son_status = $request->son_status;
        $son_album = $request->son_album;
        $user_id = $request->user_id;

        try{
            $song = Song::where('son_spotify_id','=',$son_spotify_id)->first();
            
            if($song!=null){
                return response()->json([
                    'error' => "Ja existeix aquesta cançó"
                ], 400);
            }

            $user = User::where('id','=',$user_id)->first();

            if($user==null){
                return response()->json([
                    'error' => "No existeix aquest usuari"
                ], 400);
                if ($user->user_tokens < 100){
                    return response()->json([
                        'error' => "No tens tokens"
                    ], 400);
                }
            }

            $song = new Song;
            $song->son_spotify_id = $son_spotify_id;
            $song->son_artist1 = $son_artist1;
            $song->son_artist2 = $son_artist2;
            $song->son_name = $son_name;
            $song->son_duration = $son_duration;
            $song->son_status = $son_status;
            $song->son_img = $son_img;
            $song->son_album = $son_album;
            
            $song->save();

            if ($song->son_status == 'p'){
                $petition = new Petition();
                $petition->pet_song_id = $song->id;
                $petition->save();
                $petition_queue = new Petition_queue();
                $petition_queue->pq_song_id = $song->id;
                $petition_queue->pq_users_id = $user_id;
                $datetime = new DateTime();
                $petition_queue->pq_moment_temp = $datetime->format('Y-m-d H:i:s');
                $petition_queue->save();
                $user->user_tokens = $user->user_tokens - 100;
            }

            return response()->json([
                'success' => "ok"
            ], 200);

        } catch(\Illuminate\Database\QueryException $ex){
            return response()->json([
                'database_error' => "Database error ".$ex
            ], 400);

        }catch(\Throwable $ex){
            return response()->json([
                'error' => 'General error '.$ex
            ], 400);
        }
    }

    public function updateSong(request $request){
        $son_id = $request->son_id;
        $son_status = $request->son_status;

        try{
            $song = Song::where('id','=',$son_id)->first();
            
            if($song==null){
                return response()->json([
                    'error' => "No existeix aquesta cançó"
                ], 400);
            }
            $status = $song->son_status;
            $song->son_status = $son_status;
            $song->save();

            if ($status == 'p'){
                $petition_queue = Petition_queue::where('pq_song_id','=',$son_id)->first();
                $petition_queue->delete();

                $petition = Petition::where('pet_song_id','=',$son_id)->first();
                $petition->delete();
            } else if ($son_status == 'w'){
                $var_token = SpotifyToken::find(1);

                $response = Http::withHeaders([
                    'Authorization' => 'Bearer '.$var_token->st_token,
                    'Content-Type' => 'application/json',
                ])->post('https://api.spotify.com/v1/playlists/1VQ1joMy8snbNPlndNI2iU/tracks?=uris=spotify%3Atrack%3A'.$song->son_spotify_id, [
                    'uris' => 'spotify:track:'.$song->son_spotify_id
                ]);
            }
            return response()->json([
                'success' => "ok"
            ], 200);

        } catch(\Illuminate\Database\QueryException $ex){
            return response()->json([
                'database_error' => "Database error ".$ex
            ], 400);

        }catch(\Throwable $ex){
            return response()->json([
                'error' => 'General error '.$ex
            ], 400);
        }

    }
}