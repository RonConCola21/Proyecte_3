<?php

namespace App\Http\Controllers;
use App\Models\Queue;
use Illuminate\Http\Request;

class Queuecontroller extends Controller
{
    public function getQueue(){
        try{
            $data = Queue::all();
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

    public function createQueue(Request $request){
        try{
            $que_song_id = $request->que_song_id;
            $que_users_id = $request->que_users_id;
            $que_moment_temp = $request->que_date;
            $petition = new Queue;
            $petition->que_song_id = $que_song_id;
            $petition->que_users_id = $que_users_id;
            $petition->que_moment_temp = $que_moment_temp;
            $petition->save();
            
            return response()->json([
                'success' => "ok"
            ], 200);
        } catch (\Illuminate\Database\QueryException $ex){
            return response()->json([
                'database_error' => "Database error: ".$ex
            ], 400);
        }catch(\Throwable $ex){
            return response()->json([
                'error' => "General error: ".$ex
            ], 400);
        }
    }
}
