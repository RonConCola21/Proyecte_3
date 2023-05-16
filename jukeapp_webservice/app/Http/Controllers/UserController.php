<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\User;

class UserController extends Controller
{
    public function getUsers(){
        try{
            $data = User::all();
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

    public function getUser(Request $request){
        try{
            $user_nick = $request->user_nick;
            $user_password = $request->user_password;
            $data = User::where('user_nick','=',$user_nick)->first();

            if ($data==null){
                return response()->json([
                    'error' => "Login incorrecte"
                ], 400);
            }
            $password = $data->user_password;
            
            if($password!=$user_password){
                return response()->json([
                    'error' => "Login incorrecte"
                ], 400);
            }
        } catch (\Illuminate\Database\QueryException $ex){
            return response()->json([
                'database_error' => "Database error: ".$ex
            ], 400);
        }catch(\Throwable $ex){
            return response()->json([
                'error' => "General error: ".$ex
            ], 400);
        }

        return response()->json([
            'success' => "ok"
        ], 200);;
    }

    public function setUser(Request $request){
        $user_nick = $request->user_nick;
        $user_email = $request->user_email;
        $user_password = $request->user_password;

        try{
            $user = User::where('user_nick','=',$user_nick)->first();
            
            if($user!=null){
                return response()->json([
                    'error' => "Ja existeix un usuari amb aquest alias"
                ], 400);
            }
            $user = new User;
            $user->user_nick = $user_nick;
            $user->user_email = $user_email;
            $user->user_password = $user_password;
            $user->save();

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

    public function updateUser(Request $request){
        $user_id = $request->user_id;
        $user_nick = $request->user_nick;
        $user_email = $request->user_email;
        $user_password = $request->user_password;

        try{
            $user = User::where('id','=',$user_id)->first();

            if($user==null){
                return response()->json([
                    'error' => "No existeix l'usuari"
                ], 400);
            }
            
            $user->user_nick = $user_nick;
            $user->user_email = $user_email;
            $user->user_password = $user_password;

            $user->save();

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
