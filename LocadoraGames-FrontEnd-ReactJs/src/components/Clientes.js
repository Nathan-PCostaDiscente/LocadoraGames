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

export default function Cliente() {
    const paperStyle={padding:'50px 20px', width:600,margin:"20px auto"}
    const[nome,setNome]=useState('')
    const[endereco,setEndereco]=useState('')
    const[sexo,setSexo]=useState('')
    const[idade,setIdade]=useState('')
    const[clientes,setClientes]=useState([])
     const classes = useStyles();

  const handleClick=(e)=>{
    e.preventDefault()
    const cliente={nome,endereco,sexo,idade}
    console.log(cliente)
    fetch("http://localhost:8080/cliente/adicionar",{
      method:"POST",
      headers:{"Content-Type":"application/json"},
      body:JSON.stringify(cliente)

  }).then(()=>{
    console.log("Novo cliente adicionado")
  })
}

useEffect(()=>{
  fetch("http://localhost:8080/cliente/mostrar")
  .then(res=>res.json())
  .then((result)=>{
    setClientes(result);
  }
)
},[])
  return (

    <Container>
        <Paper elevation={3} style={paperStyle}>
            <h1> Cadastro do cliente</h1>

    <form className={classes.root} noValidate autoComplete="off">
    
      <TextField id="outlined-basic" label="Nome do cliente" variant="outlined" fullWidth 
      value={nome}
      onChange={(e)=>setNome(e.target.value)}
      />
      <TextField id="outlined-basic" label="Endereço do cliente" type="text" variant="outlined" fullWidth
      value={endereco}
      onChange={(e)=>setEndereco(e.target.value)}
      />
      <TextField id="outlined-basic" label="Sexo do cliente" variant="outlined" fullWidth 
      value={sexo}
      onChange={(e)=>setSexo(e.target.value)}
      />
      <TextField id="outlined-basic" label="Idade do cliente" variant="outlined" fullWidth 
      value={idade}
      onChange={(e)=>setIdade(e.target.value)}
      />
      <Button variant="contained" color="secondary" onClick={handleClick}>
  Cadastrar
</Button>
    </form>
   
    </Paper>
    <h1>Clientes</h1>

    <Paper elevation={3} style={paperStyle}>

      {clientes.map(cliente=>(
        <Paper elevation={6} style={{margin:"10px",padding:"15px", textAlign:"left"}} key={cliente.id}>
         Id:{cliente.id}<br/>
         Nome do cliente:{cliente.nome}<br/>
         Endereço do cliente:{cliente.endereco}<br/>
         Sexo do cliente:{cliente.sexo}<br/>
         Idade do cliente:{cliente.idade}

        </Paper>
      ))
}


    </Paper>



    </Container>
  );
}
