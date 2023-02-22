import React, { useEffect, useState } from 'react';
import { makeStyles } from '@material-ui/core/styles';
import TextField from '@material-ui/core/TextField';
import { Container ,Paper,Button} from '@material-ui/core';

const useStyles = makeStyles((theme) => ({
  root: {
    '& > *': {
      margin: theme.spacing(1),
     
    },
  },
}));

export default function Jogo() {
    const paperStyle={padding:'50px 20px', width:600,margin:"20px auto"}
    const[nome,setNome]=useState('')
    const[quantidade,setQuantidade]=useState('')
    const[consoles,setConsoles]=useState('')
    const[generos,setGeneros]=useState('')
    const[idadem,setIdadem]=useState('')
    const[jogos,setJogos]=useState([])
     const classes = useStyles();

  const handleClick=(e)=>{
    e.preventDefault()
    const jogo={nome,quantidade,consoles,generos,idadem}
    console.log(jogo)
    fetch("http://localhost:8080/jogo/adicionar",{
      method:"POST",
      headers:{"Content-Type":"application/json"},
      body:JSON.stringify(jogo)

  }).then(()=>{
    console.log("Novo jogo selecionado")
  })
}

useEffect(()=>{
  fetch("http://localhost:8080/jogos/mostrar")
  .then(res=>res.json())
  .then((result)=>{
    setJogos(result);
  }
)
},[])
  return (

    <Container>
        <Paper elevation={3} style={paperStyle}>
            <h1> Selecionar jogo</h1>

    <form className={classes.root} noValidate autoComplete="off">
    
      <TextField id="outlined-basic" label="Nome do jogo" variant="outlined" fullWidth 
      value={nome}
      onChange={(e)=>setNome(e.target.value)}
      />
      <TextField id="outlined-basic" label="Quantidade de jogos" variant="outlined" fullWidth 
      value={quantidade}
      onChange={(e)=>setQuantidade(e.target.value)}
      />
      <TextField id="outlined-basic" label="Consoles para o jogo" variant="outlined" fullWidth 
      value={consoles}
      onChange={(e)=>setConsoles(e.target.value)}
      />
      <TextField id="outlined-basic" label="Gêneros do jogo" variant="outlined" fullWidth
      value={generos}
      onChange={(e)=>setGeneros(e.target.value)}
      />
      <TextField id="outlined-basic" label="Idade mínima para o jogo" variant="outlined" fullWidth 
      value={idadem}
      onChange={(e)=>setIdadem(e.target.value)}
      />
      <Button variant="contained" color="secondary" onClick={handleClick}>
  Cadastrar
</Button>
    </form>
   
    </Paper>
    <h1>Jogos cadastrados</h1>

    <Paper elevation={3} style={paperStyle}>

      {jogos.map(jogo=>(
        <Paper elevation={6} style={{margin:"10px",padding:"15px", textAlign:"left"}} key={jogo.id}>
         Id:{jogo.id}<br/>
         Nome:{jogo.nome}<br/>
         Quantidade:{jogo.quantidade}<br/>
         Consoles:{jogo.consoles}<br/>
         Gêneros do jogo:{jogo.generos}<br/>
         Idade mínima:{jogo.idadem}

        </Paper>
      ))
}


    </Paper>



    </Container>
  );
}
