// see: http://mrhaki.blogspot.fr/2009/08/groovy-goodness-working-with-files.html
def file3 = new File('groovy3.txt')
// Or a writer object:
file3.withWriter('UTF-8') { writer ->
    writer.write('We can also use writers to add contents.\r\n')
}
// Using the leftShift operator:
file3 << 'See how easy it is to add text to a file.\r\n'

int count = 0;
file3.withReader { reader ->
while (line = reader.readLine()) {
    println line
    switch (count) {
        case 0:
            assert 'We can also use writers to add contents.' == line
            break
            
            case 1:
            assert 'See how easy it is to add text to a file.' == line
            break
        }
        count++
    }
} // end of while