<?php

namespace App\Http\Controllers;
use App\Models\Petition;
use Illuminate\Http\Request;

class Petitioncontroller extends Controller
{
    public function getPetitions(){
        try{
            $data = Petition::all();
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

    public function createPetition($value){
        try{
            $data = Petition::where('pet_song_id','=',$value)->get();

            if (count($data) > 0){
                return response()->json([
                    'error' => "Ja existeix una petició per aquesta cançó"
                ], 400);
            }
            $petition = new Petition;
            $petition->pet_song_id = $value;
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

    public function removePetition(Request $request){
        try{
            $petition = Petition::where('pet_song_id', $request->pet_song_id)->first();
            
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
