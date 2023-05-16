<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

use App\Http\Controllers\Controller;
use App\Models\Song;
use Illuminate\Support\Facades\Hash;
use Tymon\JWTAuth\Facades\JWTAuth;

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

        try{
            $song = Song::where('son_spotify_id','=',$son_spotify_id)->first();
            
            if($song!=null){
                return response()->json([
                    'error' => "Ja existeix aquesta cançó"
                ], 400);
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
            $song->son_status = $son_status;
            $song->save();

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