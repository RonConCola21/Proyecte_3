<?php

namespace App\Http\Controllers;
use App\Models\Petition_queue;
use Illuminate\Http\Request;

class PetitionQueueController extends Controller
{
    public function getPetitionsQueue(){
        try{
            $data = Petition_queue::all();
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

    public function createPetitionQueue(Request $request){
        try{
            $petition = new Petition_queue;
            $petition->pq_song_id = $request->pq_song_id;
            $petition->pq_users_id = $request->pq_users_id;
            $petition->pq_moment_temp = $request->pq_date;
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

    public function removePetitionQueue(Request $request){
        try{
            $petition = Petition_queue::where('pq_song_id', $request->pq_song_id)->first();
            
            if($petition==null){
                return response()->json([
                    'Error' => "No existeix aquesta petició"
                ], 400);
            }

            $petition->delete();
            
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
