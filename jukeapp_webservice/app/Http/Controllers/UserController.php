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
            'user_id' => $data->id,
            'success' => "ok",
            'user_tokens' => $data->user_tokens
        ], 200);
    }

    public function setUser(Request $request){
        try{
            $user_nick = $request->user_nick;
            $user_email = $request->user_email;
            $user_password = $request->user_password;

            $user = User::where('user_nick','=',$user_nick)->first();
            
            if($user!=null){
                return response()->json([
                    'error' => "Ja existeix un usuari amb aquest alias"
                ], 400);
            }

            $user = User::where('user_email','=',$user_email)->first();

            if($user!=null){
                return response()->json([
                    'error' => "Ja existeix un usuari amb aquest correu electrònic"
                ], 400);
            }

            $user = new User;
            $user->user_nick = $user_nick;
            $user->user_email = $user_email;
            $user->user_password = $user_password;
            $user->save();

            $usuario = User::where('user_nick','=',$user_nick)->first();

            return response()->json([
                'user_id' => $usuario->id,
                'success' => "ok",
                'user_tokens' => $usuario->user_tokens
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
        try{
            $user_id = $request->user_id;
            $user_nick = $request->user_nick;
            $user_email = $request->user_email;
            $user_password = $request->user_password;
			$new_password = $request->new_password;

            $user = User::where('id','=',$user_id)->first();

            if($user==null){
                return response()->json([
                    'error' => "No existeix l'usuari"
                ], 400);
            }

            if ($user_password!=$user->user_password){
                return response()->json([
                    'error' => "La contrasenya no és correcta"
                ], 400);
            }
            
            $user->user_nick = $user_nick;
            $user->user_email = $user_email;
            $user->user_password = $new_password;

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

    public function addToken(Request $request){
        try{
            $user_id = $request->user_id;
            $sum= $request->user_token;

            $user = User::where('id','=',$user_id)->first();

            if($user==null){
                return response()->json([
                    'error' => "No existeix l'usuari"
                ], 400);
            }
            $tokensactuals = $user->user_tokens;
            $suma = $tokensactuals+$sum;
            if ($suma<0){
                return response()->json([
                    'error' => "No pots tenir menys de 0 tokens"
                ], 400);
            }
            $user->user_tokens = $suma;

            $user->save();

            return response()->json([
                'success' => "ok"
            ], 200);

        } catch(\Illuminate\Database\QueryException $ex){
            return response()->json([
                'database_error' => "Database error ".$ex
            ], 400);
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
