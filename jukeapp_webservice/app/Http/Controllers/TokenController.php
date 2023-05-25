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

    public function removeToken(Request $request){
        try{
            $at_cadena = $request->at_cadena;

            $user_id = $request->user_id;

            $user = DB::table('users')->where('id', $user_id)->first();

            if ($user == null){
                return response()->json([
                    'error' => "User not found"
                ], 400);
            }

            $data = DB::table('active_token')->where('at_cadena', $at_cadena)->first();

            if ($data == null){
                return response()->json([
                    'error' => "Token not found"
                ], 400);
            }

            DB::table('active_token')->where('at_cadena', $at_cadena)->delete();

            $tokens = $user->user_tokens;

            $tokens += 50;

            DB::table('users')->where('id', $user_id)->update(['user_tokens' => $tokens]);

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
