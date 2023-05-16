<?php

namespace App\Http\Controllers;
use App\Models\Token;
use Illuminate\Support\Facades\DB;

use Illuminate\Http\Request;

class TokenController extends Controller
{
    public function getToken($at_cadena){
        try{
            $data = Token::where('at_cadena','=',$at_cadena)->get();
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

    public function removeToken($at_cadena){
        try{
            $data = DB::table('active_token')->where('at_cadena', $at_cadena)->delete();
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

}
